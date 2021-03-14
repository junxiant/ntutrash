from tkinter import *
from tkinter import messagebox
from tkinter import simpledialog

root = Tk()

img_mac = PhotoImage(file='')#copy img to folder and copy path and copy paste in file=' ', img in png format
placeHolder1 = Label(root, image=img_mac) #Display the selected store pic
placeHolder1.grid(row=2, column=0)

placeHolder2 = Label(root, text="menu price") #Display the selected store menu
placeHolder2.grid(row=2, column=1)


def queue_window(): #Query the user for amount of people in queue, calculates the estimated waiting time, displays it after the user input
    no_of_ppl = simpledialog.askinteger("Waiting Time", "Enter the amount of people in queue")
    time = no_of_ppl * 3
    a = "Hi, the estimated waiting time is {} minutes.".format(time)
    messagebox.showinfo("Estimated Waiting Time", a)


def op_hours_window(): #Display the operating hours of stores
    return messagebox.showinfo("Operating Hours", "Weekdays: 8am - 11pm \n Weekends: 8am - 10pm")

waitTimeButton = Button(root, text="Waiting Time", command=queue_window)  #Opens the waiting time window
waitTimeButton.grid(row=1, column=0)

opHoursButton = Button(root, text="Operating Hours", command=op_hours_window)  #Opens the operating hours window
opHoursButton.grid(row=1, column=1)

backButton = Button(root, text="Back", command=queue_window) #Back button returns to parent window
backButton.grid(row=5, column=1)

root.geometry("300x300")
root.mainloop()


