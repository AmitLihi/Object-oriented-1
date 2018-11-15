# Object-oriented-1
Report assignment 1: 
Programed by: Amit Bibi and Lihi Belfer.
Work Space: eclipse.
We gathered information from YouTube, google, Riemann integral Desmos (to check proper output), Gral library from github .
In this project there are two main classes: “Monom” and “Polynom”.
in this report, we will interduce the main functions that runs in each class.
Monom 
This class contains a Monom object of the shape a*x^b .
We decided to make a string constructor which helps build the Polynom.  Monom class has the following arithmetic functions: 
F function, add, subtract, multiply, derivative and the usual function as: getters, setters and toString.
Polynom
This class contains a list of Monoms which called ”Polynom”.
We decided to make three functions that called to each other one by one which helped us organized and fix the Polynom after every iteration. 
The functions are:
 “arrange” which make sure that every Monom with same power combine with others.
“sort” which sorts the array list with “Monom_Comperator”.
“fixString” which fixes the string of the all Polynom.                   
Polynom class implements Polynom_able interface has the following arithmetic functions:
F function, add Monom, add Polynom, subtract, multiply, derivative, root, area,polygraph,checkpoint, areaNegative, isZero, equals and the usual function as: getters, setters and toString.
