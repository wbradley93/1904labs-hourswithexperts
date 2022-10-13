package com.labs1904.hwe.exercises

object StretchProblems {

  /*
  Checks if a string is palindrome.
 */
  def isPalindrome(s: String): Boolean = {
    if (s.length < 2) {
      true
    } else if (s.head != s.charAt(s.length - 1)) {
      false
    } else {
      isPalindrome(s.tail.dropRight(1))
    }
  }

  /*
For a given number, return the next largest number that can be created by rearranging that number's digits.
If no larger number can be created, return -1
 */
  def getNextBiggestNumber(i: Integer): Int = {
    val permutations = i.toString.permutations.toArray.sorted
    val solutionIdx = permutations.indexOf(i.toString) + 1
    if (solutionIdx == permutations.length) {
      -1
    } else {
      permutations(solutionIdx).toInt
    }
  }

}
