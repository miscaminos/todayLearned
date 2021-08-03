from tkinter import *
from turtle import *
from PIL import Image, ImageTk, ImageFilter
import time
import random


bullets=[]

class Ball:
    
    def __init__(self, canvas, size, color, x, y, xspeed, yspeed):
        self.x = x
        self.y = y 
        self.color = color
        self.size = size
        self.xspeed = xspeed #1초에 x-dir으로 움직이는 속도
        self.yspeed = yspeed
        self.canvas = canvas
        self.id = canvas.create_oval(x, y, x+size, y+size, fill=color)
        
    def move(self):
        self.canvas.move(self.id, self.xspeed, self.yspeed)
        (x1, y1, x2, y2) = self.canvas.coords(self.id) #현재 위치 반환
        (self.x, self.y) = (x1, y1)
        if x1 <=0 or x2 >= w:
            self.xspeed = -self.xspeed
        if y1 <=0 or y2 >= h:
            self.yspeed = -self.yspeed
            
    def coord_location(self):
        (x1, y1, x2, y2) = self.canvas.coords(self.id) #현재 위치 반환
        (self.x, self.y) = (x1, y1)

def fire(event):
    missle = Ball(canvas, 10, "red",10,300,20,0)
    bullets.append(missle)

# create window & canvas
w = 800
h = 400
window = Tk()
canvas = Canvas(window, width=w, height=h)
canvas.pack()
canvas.bind("<Button-1>", fire)

img = PhotoImage(file="car.gif") # shooter object
img = img.subsample(5, 5)
canvas.create_image(10,300,anchor=NW, image=img)

ballT = Ball(canvas, 40,"red",500,200,0,5)# target ball
timer=0

# practice3:
# need to update on handling collision of bullets and ballT
while True:
    for b in bullets: 
        b.move()
        b.coord_location
        if (b.x + b.size) >= w:
            canvas.delete(b.id)
            bullets.remove(b) 
        ballT.move()
        ballT.coord_location
        if ((b.x >= ballT.x-ballT.size and b.x <= ballT.x+ballT.size)  or \
                 (b.y >= ballT.y-ballT.size and b.y <= ballT.y+ballT.size)):
            print("targeted!!")
            canvas.delete(b.id)
            bullets.remove(b) 
            canvas.delete(ballT.id)
            bullets.remove(ballT) 
    window.update()
    time.sleep(0.03)
    timer+=1
    if timer>=500:
        window.destroy()
        print("timeout!!")
        break;


# practice2:
# try randomly creating n개의 balls and bouncing them randomly
color_list=["red","orange","yellow","green","blue","purple","navy","black"]
ball_list=[]
timer=0

for i in range(100):
    color = random.choice(color_list)
    size = random.randint(1,50)
    x=random.randint(1,w)
    y=random.randint(1,h)
    xspeed=random.randint(5,20)
    yspeed=random.randint(5,20)
    ball_list.append(Ball(canvas, size, color, x, y, xspeed, yspeed))
    
while True:
    for ball in ball_list:
        ball.move()
    window.update()
    time.sleep(0.03)
    timer+=1
    if timer>=300:
        window.destroy()
        break;
    
# practice1:
# practice creating and printing ball information
ballS = Ball(30,"green",-100,0,10,0)
ballT = Ball(canvas, 30,"red",0,0,10,0)

print("target ball")
print(ballT.color)
print(ballT.size)
print(ballT.x)
print(ballT.y)

print("shooting ball")
print(ballS.color)
print(ballS.size)
print(ballS.x)
print(ballS.y)
