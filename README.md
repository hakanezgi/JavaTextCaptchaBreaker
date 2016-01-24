# JavaTextCaptchaBreaker
TextCaptchaBreaker program implemented in Java programming language.

This project is inspired from https://github.com/kbhomes/TextCaptchaBreaker.

Unlike the original one which tries to find the correct answer in its first try, this version focuses on finding all possible answers to determine the overall solvability of the test. 

Statistics for 1000 random textCAPTCHA tests are given below:

Success on first try = 753 <br />
Success using possible answers list = 239 <br />
Total number of tries when possible answers list is used = 1136 <br />
Fail to solve = 8 <br />

Failed tests are: <br />
Of the numbers 78, seventy three, one hundred or nineteen, which is the largest? <br />
Of the numbers 59, 9, eighty or ninety two, which is the largest? <br />
5, 7 and thirty seven: the 3rd number is? <br />
The 5th number from twenty two, 20, four, 21 and thirty eight is? <br />
Of the numbers sixty five, 36, nineteen, 32, seven or ninety nine, which is the highest? <br />
Of the numbers eighty seven, ninety five, forty four or four, which is the lowest? <br />
Of the numbers seventy two, eighty six or 46, which is the biggest? <br />
Of the numbers 55, fifty one, eighty eight or sixty eight, which is the biggest?
