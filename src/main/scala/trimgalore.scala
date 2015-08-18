package ohnosequencesBundles.statika

import ohnosequences.statika._, bundles._, instructions._
import java.io.File


abstract class Trimgalore (val version: String) extends Bundle(cutadapt) {

  val usrbin = "/usr/bin/"
  val trimgaloreDir = "trim_galore_zip"
  val cmd = "trim_galore"


  def install: Results = {
    Seq("wget", s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/trimgalore/${version}/trim_galore_v${version}.zip" ) ->-
    Seq("unzip", s"trim_galore_v${version}.zip") ->-
    Seq("ln", "-s", new File(s"${trimgaloreDir}/${cmd}").getAbsolutePath, s"${usrbin}/${cmd}") ->-
    success(s"${bundleName} is installed")
  }

}
