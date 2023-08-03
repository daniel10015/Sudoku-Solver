: run.sh <custom>
: FOR JAVA
: compiles then runs the Solver.java (requires a Board.txt in cd)
:  -- Color Codes -- (Syntax: "Color {color_code(background)color_code{text}}")
: 0 = Black    |   8 = Gray
: 1 = Blue     |   9 = Light Blue
: 2 = Green    |   A = Light Green
: 3 = Aqua     |   B = Light Aqua
: 4 = Red      |   C = Light Red
: 5 = Purple   |   D = Light Purple
: 6 = Yellow   |   E = Light Yellow

echo Running Sudoku Solver...

javac Solver.java
java Solver %1