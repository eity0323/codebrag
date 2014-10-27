package com.softwaremill.codebrag.common

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.Random
import org.apache.commons.codec.binary.Hex
import java.io.File

object Utils {

  val OneWeek = 7 * 24 * 3600
  val DateFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

  def md5(s: String): String = {
    val m = java.security.MessageDigest.getInstance("MD5")
    val b = s.getBytes("UTF-8")
    m.update(b, 0, b.length)
    Hex.encodeHexString(m.digest())
  }

  def sha1(s: String): String = {
    val m = java.security.MessageDigest.getInstance("SHA-1")
    val b = s.getBytes("UTF-8")
    m.update(b, 0, b.length)
    new java.math.BigInteger(1, m.digest()).toString(16)
  }

  def sha256(password: String, salt: String): String = {
    val md = java.security.MessageDigest.getInstance("SHA-256")
    md.reset()
    md.update(salt.getBytes("UTF-8"))
    new java.math.BigInteger(1, md.digest(password.getBytes("UTF-8"))).toString(16)
  }

  def checkbox(s: String): Boolean = {
    s match {
      case null => false
      case _ => s.toLowerCase == "true"
    }
  }

  def format(dateTime: DateTime): String = {
    DateFormat.print(dateTime)
  }

  def randomString(length: Int) = {
    val sb = new StringBuffer()
    val r = new Random()

    for (i <- 1 to length) {
      sb.append((r.nextInt(25) + 65).toChar) // A - Z
    }

    sb.toString
  }

  def rmMinusRf(file: File) = {
    Runtime.getRuntime.exec(Array[String]("rm", "-rf", file.getAbsolutePath)).waitFor == 0
  }
}

object Joda {
    implicit def dateTimeOrdering: Ordering[DateTime] = Ordering.fromLessThan(_ isBefore _)
}