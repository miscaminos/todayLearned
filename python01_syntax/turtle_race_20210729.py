import turtle
import random

size=3
t1 = turtle.Turtle()
t1.shape("turtle")
t1.color("pink")
t1.pensize(size)
t1.shapesize(size)
t1.up(); t1.goto(-350,60); t1.down()

t2 = turtle.Turtle()
t2.shape("turtle")
t2.color("blue")
t2.pensize(size)
t2.shapesize(size)
t2.up(); t2.goto(-350,-60); t2.down()

end_size = 15
for i in range(50):
    t1.fd(random.randint(1,end_size))
    t2.fd(random.randint(1,end_size))

x_t1, y_t1 = t1.position()
x_t2, y_t2 = t2.position()

#결과 표기
if x_t1 > x_t2:
    t1.write("        1위", font=("Arial", 20, "normal"))
elif x_t2 > x_t1:
    t2.write("        1위", font=("Arial", 20, "normal"))
else:
    t1.write("        tie")
    t2.write("        tie")
