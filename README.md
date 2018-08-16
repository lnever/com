# naive-interpreter

多年前的编译原理作业

A interpreter of a function drawing language.

Still some bugs remain.

It can draw a image of some function (like pow,sin,cos,ln and the nesting of them) using the following code.

rot is x;          //change the rot to specific double x

scale is (kx,ky);    //change the scale of x and y

origin is (ox,oy);     // change the origin

for T from lb to ub step draw (f(T),f'(T));  
// set the lowerbound and upperbound and the step of the var T,and finally draw a image with functions f(t) and f'(t)
