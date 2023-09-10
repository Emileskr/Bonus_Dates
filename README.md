# Date Palindrome calculator

Task by Emilė Skrupskaitė

## Description
This program prints dates that are the same backwards as they are forwards.
The dates are printed between two years that are given as an input.
After checking if the input is valid, the program calculates the dates and 
checks if they are "Bonus" dates - that means they are the same forwards as they are 
backwards. 

This program also checks if the year has no chance to have a Bonus date:
if the second digit is bigger than 3 (because a month cannot have more than 31 day)
or the 4th digit is bigger than 1 (because a year cannot have more than 12 months).

For example: if the year is 1457, it will not have any Bonus dates, because
any date in reverse would be (lets take 1st of January) 1010-75-41. As we all know, there
is no 75th month or 41st day.

In that case a year is skipped without checking every date in it.
This characteristic lets the program work faster 
by avoiding pointless loops. 

## How to run
Compile the program
```sh
javac src/main/BonusDates.java -d out
```

Run the code
```sh
java -cp out BonusDates {{fromYear}} {{toYear}}
```
Here is an example starting with year 2000 and going to year 3000
```sh
java -cp out BonusDates 2000 3000
```

## Limitations
This program works with numbers between 1 and 2147483647 (maximum positive integer value).

## Space for improvement
Calculations could be made to skip more than one year at once if needed.
That would make the program even faster if we wanted to check a big range
of dates.