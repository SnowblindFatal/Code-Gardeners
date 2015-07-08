// Generated by CoffeeScript 1.9.3
(function() {
  var WebOmi;

  WebOmi = (function($, my) {
    my.codeMirrorSettings = {
      mode: "text/html",
      lineNumbers: true,
      lineWrapping: true
    };
    return my;
  })($, WebOmi || {});

  $(function() {
    return WebOmi = (function($, my) {
      my.requestCodeMirror = CodeMirror.fromTextArea($("#requestArea")[0], my.codeMirrorSettings);
      my.responseCodeMirror = CodeMirror.fromTextArea($("#responseArea")[0], my.codeMirrorSettings);
      my.odfTree = $('#nodetree');
      my.requestSel = $('.requesttree');
      my.readAllBtn = $('#readall');
      my.odfTree.jstree({
        plugins: ["checkbox"]
      }).on("changed.jstree", function(_, data) {
        return console.log(data.node);
      });
      my.requestSel.jstree({
        core: {
          themes: {
            icons: false
          },
          multiple: false
        }
      }).on("changed.jstree", function(_, data) {
        return console.log(data.node.id);
      });
      return my.readAllBtn.on('click', my.requests.readAll(true));
    })($, WebOmi);
  });

}).call(this);
