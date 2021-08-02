from tkinter import *

def paint(event):
    global prev_x, prev_y, color
    if prev_x != -1:
        canvas.create_line(prev_x, prev_y, event.x, event.y, fill=color) # draw Line
    prev_x, prev_y = event.x, event.y                                   # save Position

def init_var(event):
    global prev_x, prev_y, color
    prev_x, prev_y = event.x, event.y                                   # save Position
    canvas.create_oval(prev_x, prev_y, prev_x, prev_y, outline=color, fill=color)      # draw Point

def change_color(c):
    global color; 
    color = c
                        
window = Tk()

prev_x, prev_y = -1, -1                                                 # Previus Position
color = "black"
canvas = Canvas(window, width=640, height=480)
canvas.bind("<B1-Motion>", paint)                                       # Move
canvas.bind("<Button-1>", init_var)                                     # Press
canvas.grid(row=0, column=0, columnspan=4)

button = Button(window, text="Red", highlightbackground = "red",
                fg = "red", command=lambda:change_color("red"))
button.grid(row=1, column=0)

button = Button(window, text="Green", highlightbackground = "green",
                fg = "green", command=lambda:change_color("green"))
button.grid(row=1, column=1)

button = Button(window, text="Blue", highlightbackground = "blue",
                fg = "blue", command=lambda:change_color("blue"))
button.grid(row=1, column=2)

button = Button(window, text="Black", highlightbackground = "black",
                fg = "black", command=lambda:change_color("black"))
button.grid(row=1, column=3)

window.mainloop()
