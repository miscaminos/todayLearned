import time
import random
import turtle

def problem1():
    now = time.time()
    thisyear = int(1970+now//(365*24*60*60))
    print("올해는 "+str(thisyear)+ "입니다.")
    print("올해는 ",thisyear, "입니다.")

def problem2():
    sum=0
    x = input("숫자 정수를 입력해주세요.")
    for i in range(len(x)):
        sum+= int(x[i])
    print(sum)

# 위 problem2를 for-loop을 사용하지 않고 해결하는 방법??
def problem2_1():
    sum=0
    x = input("숫자 정수를 입력해주세요.")        
    if x<9999 and x>1000:
        sum += x//1000
        sum += x//100
        sum += x/10
        sum += x%10
    print(sum)


def problem3():
    year = input("년도를 입력하세요.")
    year = int(year)
    if (year%100 != 0 and ((year%4 == 0) or (year%400 == 0))):
        print(str(year)+"는 윤년입니다.")
    else:
        print(str(year)+"는 윤년이 아닙니다.")


def problem4():
    r = random.randrange(2)
    if r == 1:
        print("front")
    else:
        print("back")

def problem5():
    options = ["left", "center", "right"]
    y = random.choice(options)
    if y=="left":
        print("왼쪽")
    elif y=="right":
        print("오른쪽")
    else:
        print("골")
        
# 도형 그리기
#t= turtle.Turtle()
#t.shape("turtle")
   
def problem6():
    s=turtle.textinput("","도형을 입력하세요")
    if s =="사각형":
        w=int(turtle.textinput("","가로길이는?"))
        h=int(turtle.textinput("","세로높이는?"))
        t.fd(w)
        t.left(90)
        t.fd(h)
        t.left(90)
        t.fd(w)
        t.left(90)
        t.fd(h)

def problem7():
    s=turtle.textinput("","별 사이즈를 입력하세요")
    t.right(75)
    for i in range(5):
        t.fd(int(s))
        t.left(145)

def problem8():
    s=int(turtle.textinput("","원하는 원의 갯수를 입력하세요"))
    for r in range(s):
        t.circle(100)
        t.left(360/s)

def problem9():
    s=int(turtle.textinput("","움직일 횟수를 입력하세요"))
    for i in range(s):
        t.fd(random.randint(1,100))
        direction = random.choice(["left","right"])
        if direction == "left":
            t.left(random.randrange(-180,180,step=10))
        else:
            t.right(random.randrange(-180,180,step=10))
        
# 구구단    
def problem10():
    for x in range(1,10):
        print("\n")
        print(x,"단:\n")
        for y in range(1,10):
            print(x,"x",y," = ",x*y)
            print("\n")

# 팩토리얼
def problem11():
    f=1
    x = int(input("factorial of what number?"))
    for n in range(1,x+1):
        f *= n 
    print(f)

def problem12():
    pw=""
    while pw != "pythonisfun":
        pw = input("what is your password?")

problem12()
    
