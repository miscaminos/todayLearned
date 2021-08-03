from turtle import *

#class methods must have "self" as parameter
class Car: 

    def __init__(self, color, brandName, model, speed=0):
        self.color = color
        self.speed = speed
        self.brandName = brandName
        self.model = model

    def __str__(self):
        msg = "speed: "+str(self.speed)+"\ncolor: "+self.color+"\nmodel: "+self.model
        return msg   
        
    def drive(self):
        self.speed += 10

myCar = Car("blue", "Mercedes", "S-class")

myCar.color = "orange"
myCar.brandName = "Mercedes"
myCar.model = "E-Class"

print(myCar.speed)
print(myCar.brandName)
print(myCar.color)
print(myCar.model)

myCar.drive()
print(myCar.speed)
message = myCar.__str__()
print(message)



class MyTurtle:
    def __init__(self, shape, radius):
        t = turtle.Turtle()
        t.shape(shape)
        t.up()
        t.goto(0,-radius)
        t.down()
        t.circle(radius)

t1 = MyTurtle("circle", 50)
t2 = MyTurtle("square", 100)
t3 = MyTurtle("triangle", 150)

class Car2:
    def __init__(self, color, speed, model, time=1):
        self.color = color
        self.speed = speed
        self.model = model
        self.time = time
        self.turtle = Turtle()
        self.turtle.shape("car.gif")
        
    def drive(self):
        self.turtle.forward(self.speed*self.time)
        
    def left_turn(self):
        self.turtle.left(90)

register_shape("car.gif") #must be .gif image file in order to be used in register_shape()
myTurtleCar = Car2("green", 5, "EV500")

for i in range(30):
    myTurtleCar.drive()
    myTurtleCar.left_turn()

class Ball:
    
    def __init__(self, color, size, speed):
        self.x=0
        self.y=0
    
        self.size=size
        
        self.xspeed=speed
        self.yspeed=speed
        
        self.color=color
        
        self.turtle=Turtle()
        self.turtle.shape("circle")
        self.turtle.color(color, color)
        self.turtle.resizemode("user")
        self.turtle.shapesize(size, size, 10)
    
    
    def glow(self):
        self.turtle.fillcolor("yellow")
        
    def off(self):
        self.turtle.fillcolor("red")
    
    def move(self):
        self.x += self.xspeed
        self.y += self.yspeed
        self.turtle.goto(self.x, self.y)
        print("(",self.x,",",self.y,")")


ball = Ball("red", 5, 10)
for i in range(20):
    ball.move()
    ball.glow()
    ball.off()
