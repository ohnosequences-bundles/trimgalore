package ohnosequencesBundles.statika

import ohnosequences.statika._
import java.io.File


abstract class Trimgalore (val version: String, val cutadapt: Cutadapt, val fastqc: FastQC) extends Bundle(cutadapt, fastqc) { trimgalore =>

  lazy val zip = s"trim_galore_v${version}.zip"
  lazy val folder = "trim_galore_zip"
  lazy val name = "trim_galore"
  lazy val binary = new File(trimgalore.folder, trimgalore.name)

  lazy val downloadZip = cmd("wget")(
    s"http://s3-eu-west-1.amazonaws.com/resources.ohnosequences.com/trimgalore/${version}/${zip}"
  )

  lazy val unzip = cmd("unzip")(zip)

  lazy val makeExecutable = cmd("chmod")("+x", trimgalore.binary.getCanonicalPath)

  lazy val linkBinary = cmd("ln")("-s", trimgalore.binary.getCanonicalPath, s"/usr/bin/${trimgalore.name}")

  def instructions: AnyInstructions = downloadZip -&- unzip -&- makeExecutable -&- linkBinary

  def runTrimGalore(args: String*): CmdInstructions = cmd("trim_galore")(args: _*)
}
