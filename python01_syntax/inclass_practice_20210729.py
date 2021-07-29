import turtle
import random

def problem1():
    colors=["yellow", "blue", "green","red","purple"]
    t = turtle.Turtle()
    turtle.bgcolor("black")
    t.width(2)
    t.speed(0)
    size = int(input("how big u want your piece to be?"))
    for r in range(10,size):
        t.pencolor(colors[r%len(colors)])
        t.fd(r*4)
        t.right(75)


def problem2():
    sum=0
    ans = input("your answer?(yes/no)")
    while ans=="yes":
        sum+=1
        ans = input("your answer?(yes/no)")   
    print(sum)


def problem3():
    sum=0
    ans="yes"
    while ans=="yes":
        num = int(input("숫자를입력하시오"))
        ans = input("계속?(yes/no)")
        sum+=num
    print(sum)


def problem4():
    count=0
    guess = 0
    target = random.randint(1,100)
    while guess != target and count < 20:
        count+=1
        guess = int(input("whatis your guess?"))
        if guess > target:
            print("try smaller number!")
        elif guess < target:
            print("try bigger number!")
        else:
            break
    if guess == target:
        print("congratulations!!")
    else:
        print("nice try.")
    print("the answer was",target,". you've guessed ",count,"times.")


def problem5():
    x = random.randint(1,100)
    y = random.randint(1,100)
    c = random.choice(["+","-","x","/"])
    print("Try solving the following problem")
    print(x,c,y,"=",end=""); guess = int(input())
    
    if c=="+":
        answer = x+y
    elif c=="-":
        answer = x-y
    elif c=="x":
        answer = x*y
    else:
        answer = x//y
    
    if answer == guess:
        print("good job!")
    else:
        print("wrong answer...")
    print("the correct answer is", answer)


# draw clock pattern-1
def problem6():
    t = turtle.Turtle()
    t.pencolor("red")
    t.shape("turtle")
    t.stamp()
    n = int(input("how many marks?(4, 12, 48)"))
    for i in range(n):
        t.up()
        t.fd(50)
        t.pendown()
        t.fd(50)
        t.stamp()
        t.up()
        t.fd(-100)
        t.left(360/n)


# draw clock pattern-2        
def problem7():
    tt = turtle.Turtle()
    tt.shape("turtle")
    tt.color("red")
    tt.stamp()
    tt.speed(0)
    for i in range(12):
        tt.up()
        tt.fd(50)
        tt.down()
        tt.fd(30)
        tt.up()
        tt.fd(20)
        tt.down()
        tt.stamp()
        
        tt.left(180)
        tt.up()
        tt.fd(100)
        
        tt.down()
        tt.left(180)
        tt.right(30)


# draw multiple separate squares
def problem8(length,c):
    t = turtle.Turtle()
    t.color(c)
    for k in range(0,201,100):
        t.goto(k,0)
        t.begin_fill()
        for i in range(4):
            t.down()
            t.fd(length)
            t.left(90)
            t.up()
        t.end_fill()        


# draw rectangles where mouse is clicked
def problem8_1(t,length,c):
    t.color(c)
    t.begin_fill()
    for i in range(4):
        t.down()
        t.fd(length)
        t.left(90)
        t.up()
    t.end_fill()

def problem9(length):
    t = turtle.Turtle()
    colors=["red","blue","purple","green","yellow"]
    for k in range(0,201,100):
        t.goto(k,0)
        cc = random.choice(colors)
        problem8_1(t,length,cc)


# draw n-polygon
def problem10(n):
    t = turtle.Turtle()
    for k in range(6):
        for i in range(n):
            t.fd(50)
            t.left(180-(180*(n-2)/n))
        t.right(20)

        
#draw colored squares on clicked spots        
def drawsquare(x,y):
    colors=["red","blue","purple","green","yellow"]
    cc = random.choice(colors)

    t = turtle.Turtle()
    t.color(cc)
    t.up()
    t.goto(x,y)
    t.begin_fill()
    for i in range(4):
        t.down()
        t.fd(25)
        t.left(90)
        t.up()
    t.end_fill()
    
def problem11():
    s=turtle.Screen()
    s.onscreenclick(drawsquare)


# draw tree <--recursive function
def tree(length,t):
    if length > 5:
        t.fd(length)
        t.right(20)
        tree(length-15,t)
        t.left(40)
        tree(length-15,t)
        t.right(20)
        t.backward(length)
        
def problem12():
    t = turtle.Turtle()
    t.left(90) #let the turtle face upward to start
    t.color("brown")
    t.speed(1)
    tree(80,t)
        

# draw a bar graph
def draw_bar(value,t):
    bar_width = 48
    t.begin_fill()
    t.left(90);  t.fd(value)
    # left
    t.write("  " + str(value), font=('Times', 20, 'bold'))
    t.right(90); t.fd(bar_width)
    # top
    t.right(90); t.fd(value)
    # right
    t.right(90); t.fd(bar_width);
    # bottom
    t.fd(-bar_width); t.left(180);
    # initialize
    t.end_fill()

def problem13():
    t = turtle.Turtle()
    t.color("blue")
    t.fillcolor("red")
    t.speed(0)
    t.pensize(3)
    t.goto(-200,-60)
    for value in [120, 56, 309, 220, 156, 23, 98]:
        draw_bar(value,t)
  
