/*
  Copyright (c) 2015 Aalto University.

  Licensed under the 4-clause BSD (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at top most directory of project.

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/
// Generated by CoffeeScript 1.9.3
(function() {
  var formLogicExt,
    hasProp = {}.hasOwnProperty;

  formLogicExt = function($, WebOmi) {
    var my;
    my = WebOmi.formLogic = {};
    my.setRequest = function(xml) {
      var mirror;
      mirror = WebOmi.consts.requestCodeMirror;
      if (xml == null) {
        mirror.setValue("");
      } else if (typeof xml === "string") {
        mirror.setValue(xml);
      } else {
        mirror.setValue(new XMLSerializer().serializeToString(xml));
      }
      return mirror.autoFormatAll();
    };
    my.getRequest = function() {
      var str;
      str = WebOmi.consts.requestCodeMirror.getValue();
      return WebOmi.omi.parseXml(str);
    };
    my.modifyRequest = function(callback) {
      var req;
      req = my.getRequest();
      callback();
      return WebOmi.requests.generate();
    };
    my.getRequestOdf = function() {
      var str;
      WebOmi.error("getRequestOdf is deprecated");
      str = WebOmi.consts.requestCodeMirror.getValue();
      return o.evaluateXPath(str, '//odf:Objects')[0];
    };
    my.clearResponse = function() {
      var mirror;
      mirror = WebOmi.consts.responseCodeMirror;
      mirror.setValue("");
      return WebOmi.consts.responseDiv.slideUp();
    };
    my.setResponse = function(xml) {
      var mirror;
      mirror = WebOmi.consts.responseCodeMirror;
      if (typeof xml === "string") {
        mirror.setValue(xml);
      } else {
        mirror.setValue(new XMLSerializer().serializeToString(xml));
      }
      mirror.autoFormatAll();
      WebOmi.consts.responseDiv.slideDown({
        complete: function() {
          return mirror.refresh();
        }
      });
      return mirror.refresh();
    };
    my.send = function(callback) {
      var consts, request, server;
      consts = WebOmi.consts;
      my.clearResponse();
      server = consts.serverUrl.val();
      request = consts.requestCodeMirror.getValue();
      consts.progressBar.css("width", "95%");
      return $.ajax({
        type: "POST",
        url: server,
        data: request,
        contentType: "text/xml",
        processData: false,
        dataType: "text",
        error: function(response) {
          consts.progressBar.css("width", "100%");
          my.setResponse(response.responseText);
          consts.progressBar.css("width", "0%");
          consts.progressBar.hide();
          return window.setTimeout((function() {
            return consts.progressBar.show();
          }), 2000);
        },
        success: function(response) {
          consts.progressBar.css("width", "100%");
          my.setResponse(response);
          consts.progressBar.css("width", "0%");
          consts.progressBar.hide();
          window.setTimeout((function() {
            return consts.progressBar.show();
          }), 2000);
          if ((callback != null)) {
            return callback(response);
          }
        }
      });
    };
    my.buildOdfTree = function(objectsNode) {
      var evaluateXPath, genData, objChildren, tree, treeData;
      tree = WebOmi.consts.odfTree;
      evaluateXPath = WebOmi.omi.evaluateXPath;
      objChildren = function(xmlNode) {
        return evaluateXPath(xmlNode, './odf:InfoItem | ./odf:Object');
      };
      genData = function(xmlNode, parentPath) {
        var child, name, path;
        switch (xmlNode.nodeName) {
          case "Objects":
            name = xmlNode.nodeName;
            return {
              id: idesc(name),
              text: name,
              state: {
                opened: true
              },
              type: "objects",
              children: (function() {
                var i, len, ref, results;
                ref = objChildren(xmlNode);
                results = [];
                for (i = 0, len = ref.length; i < len; i++) {
                  child = ref[i];
                  results.push(genData(child, name));
                }
                return results;
              })()
            };
          case "Object":
            name = WebOmi.omi.getOdfId(xmlNode);
            path = parentPath + "/" + name;
            return {
              id: idesc(path),
              text: name,
              type: "object",
              children: (function() {
                var i, len, ref, results;
                ref = objChildren(xmlNode);
                results = [];
                for (i = 0, len = ref.length; i < len; i++) {
                  child = ref[i];
                  results.push(genData(child, path));
                }
                return results;
              })()
            };
          case "InfoItem":
            name = WebOmi.omi.getOdfId(xmlNode);
            path = parentPath + "/" + name;
            return {
              id: idesc(path),
              text: name,
              type: "infoitem",
              children: [
                genData({
                  nodeName: "MetaData"
                }, path)
              ]
            };
          case "MetaData":
            path = parentPath + "/MetaData";
            return {
              id: idesc(path),
              text: "MetaData",
              type: "metadata",
              children: []
            };
        }
      };
      treeData = genData(objectsNode);
      tree.settings.core.data = [treeData];
      return tree.refresh();
    };
    my.buildOdfTreeStr = function(responseString) {
      var objectsArr, omi, parsed;
      omi = WebOmi.omi;
      parsed = omi.parseXml(responseString);
      objectsArr = omi.evaluateXPath(parsed, "//odf:Objects");
      if (objectsArr.length !== 1) {
        return alert("failed to get single Objects odf root");
      } else {
        return my.buildOdfTree(objectsArr[0]);
      }
    };
    return WebOmi;
  };

  window.WebOmi = formLogicExt($, window.WebOmi || {});

  (function(consts, requests, formLogic) {
    return consts.afterJquery(function() {
      var controls, inputVar, makeRequestUpdater, ref;
      consts.readAllBtn.on('click', function() {
        return requests.readAll(true);
      });
      consts.sendBtn.on('click', function() {
        return formLogic.send();
      });
      consts.resetAllBtn.on('click', function() {
        var child, closetime, i, len, ref;
        requests.forceLoadParams(requests.defaults.empty());
        closetime = 1500;
        ref = consts.odfTree.get_children_dom('Objects');
        for (i = 0, len = ref.length; i < len; i++) {
          child = ref[i];
          consts.odfTree.close_all(child, closetime);
        }
        return formLogic.clearResponse();
      });
      consts.ui.odf.ref.on("changed.jstree", function(_, data) {
        var odfTreePath;
        switch (data.action) {
          case "select_node":
            odfTreePath = data.node.id;
            return formLogic.modifyRequest(function() {
              return requests.params.odf.add(odfTreePath);
            });
          case "deselect_node":
            odfTreePath = data.node.id;
            formLogic.modifyRequest(function() {
              return requests.params.odf.remove(odfTreePath);
            });
            return $(jqesc(odfTreePath)).children(".jstree-children").find(".jstree-node").each(function(_, node) {
              return consts.odfTree.deselect_node(node);
            });
        }
      });
      consts.ui.request.ref.on("select_node.jstree", function(_, data) {
        var i, input, isReadReq, isRequestIdReq, len, readReqWidgets, reqName, ui;
        reqName = data.node.id;
        WebOmi.debug(reqName);
        if (reqName === "readReq") {
          return consts.ui.request.set("read");
        } else {
          ui = WebOmi.consts.ui;
          readReqWidgets = [ui.newest, ui.oldest, ui.begin, ui.end];
          isReadReq = (function() {
            switch (reqName) {
              case "readAll":
              case "read":
              case "readReq":
                return true;
              default:
                return false;
            }
          })();
          isRequestIdReq = (function() {
            switch (reqName) {
              case "cancel":
              case "poll":
                return true;
              default:
                return false;
            }
          })();
          for (i = 0, len = readReqWidgets.length; i < len; i++) {
            input = readReqWidgets[i];
            input.ref.prop('disabled', !isReadReq);
            input.set(null);
            input.ref.trigger("input");
          }
          ui.requestID.ref.prop('disabled', !isRequestIdReq);
          if (!isRequestIdReq) {
            ui.requestID.set(null);
            ui.requestID.ref.trigger("input");
          }
          ui.interval.ref.prop('disabled', reqName !== 'subscription');
          ui.interval.set(null);
          ui.interval.ref.trigger("input");
          return formLogic.modifyRequest(function() {
            var newHasMsg;
            requests.params.name.update(reqName);
            newHasMsg = requests.defaults[reqName]().msg;
            return requests.params.msg.update(newHasMsg);
          });
        }
      });
      makeRequestUpdater = function(input) {
        return function(val) {
          return formLogic.modifyRequest(function() {
            return requests.params[input].update(val);
          });
        };
      };
      ref = consts.ui;
      for (inputVar in ref) {
        if (!hasProp.call(ref, inputVar)) continue;
        controls = ref[inputVar];
        if (controls.bindTo != null) {
          controls.bindTo(makeRequestUpdater(inputVar));
        }
      }
      return null;
    });
  })(window.WebOmi.consts, window.WebOmi.requests, window.WebOmi.formLogic);

  $(function() {
    return $('.optional-parameters > a').on('click', function() {
      var glyph;
      glyph = $(this).find('span.glyphicon');
      if (glyph.hasClass('glyphicon-menu-right')) {
        glyph.removeClass('glyphicon-menu-right');
        return glyph.addClass('glyphicon-menu-down');
      } else {
        glyph.removeClass('glyphicon-menu-down');
        return glyph.addClass('glyphicon-menu-right');
      }
    });
  });

}).call(this);
