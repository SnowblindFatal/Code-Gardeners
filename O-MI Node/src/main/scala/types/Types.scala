/**
  Copyright (c) 2015 Aalto University.

  Licensed under the 4-clause BSD (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at top most directory of project.

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
**/

/** Package containing internal types used between different modules.
  *
  **/
package types

import java.sql.Timestamp
  /** case class that represents parsing error
   *  @param msg error message that describes the problem.
   */
  case class ParseError(msg: String) 

  /**
   * Path is a wrapper for Seq[String] representing path to an O-DF Object
   * It abstracts path seperators ('/') from error prone actions such as joining
   * two paths or creating new Paths from user input.
   * Path can be used as a sequence via an implicit conversion or _.toSeq
   */
  class Path(pathSeq: Vector[String]){
    import Path._
    /**
     * Removes extra path elements and holds the Path as Seq[String]
     */
    val toSeq: Seq[String] = {
      val normalized = pathSeq.filterNot(_ == "")
      normalized.toVector
    }

    def this(pathStr: String) = this{
      pathStr.split("/").toVector
    }

    /**
     * Join two paths.
     * @param otherPath other path to join at the end of this one
     * @return new path with other joined to this path
     */
    def /(otherPath: Path): Path = Path(this ++ otherPath)

    def /(otherPathStr: String): Path = {
      this / Path(otherPathStr)
    }

    /**
     * Get list of ancestors from this path, e.g "/a/b/c/d" => "/a", "/a/b", "/a/b/c", "a/b/c/d"
     * Order is from oldest descending.
     */
    def getParentsAndSelf: Seq[Path] = this.inits.map(Path(_)).toList.reverse.tail

    override def equals(that: Any): Boolean = that match{
      case thatPath:Path => thatPath.toSeq.equals(this.toSeq)
      case _ => false
    }
    override def hashCode(): Int = this.toSeq.hashCode

    /**
     * Creates a path string which represents this path with '/' seperators.
     * Representation doesn't start nor end with a '/'.
     */
    override def toString: String = this.mkString("/")
  }

  /** Helper object for Path, contains implicit conversion between Path and Seq[String]
    */
  object Path {
    def apply(pathStr: String): Path = new Path(pathStr)
    def apply(pathSeq: Seq[String]): Path = new Path(pathSeq.toVector)
    val empty = new Path(Vector.empty)


    import scala.language.implicitConversions // XXX: maybe a little bit stupid place for this

    implicit def PathAsSeq(p: Path): Seq[String] = p.toSeq
    implicit def SeqAsPath(s: Seq[String]): Path = Path(s.toVector)
  }
