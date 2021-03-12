from tkinter import *
from datetime import datetime

root = Tk()

day = StringVar(root)
mth = StringVar(root)
yr = StringVar(root)
time = StringVar(root)

dayLabel = Label(root, text="Day")
dayLabel.grid(row=1, column=0)

monthLabel = Label(root, text="Month")
monthLabel.grid(row=2, column=0)

yearLabel = Label(root, text="Year")
yearLabel.grid(row=3, column=0)

timeLabel = Label(root, text="Time (24h)")
timeLabel.grid(row=4, column=0)
#Formatting

dd = OptionMenu(root, day, "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31")
mm = OptionMenu(root, mth, "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
yy = OptionMenu(root, yr, "2019", "2020")
tt = OptionMenu(root, time, "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24")
#Drop down list to allow user to select specific date
#dd/mm/yy/tt refers to day/month/year/time, ideal to make month decide the amount of days to display

dd.grid(row=1, column=3)
mm.grid(row=2, column=3)
yy.grid(row=3, column=3)
tt.grid(row=4, column=3)


def date(): #Get the date input from user and convert it to date time format
    dd = day.get()
    mm = mth.get()
    yy = yr.get()
    tt = time.get()
    datei = dd+mm+yy+tt
    date = datetime.strptime(datei, '%d%m%Y%H')
    #date_time = datetime.strptime(datei, '%d%m%Y%H')
    print(date) #use this function with the "get day of week+AM/PM" function


def day_of_week():
    dd = day.get()
    mm = mth.get()
    yy = yr.get()
    datei = dd + mm + yy
    date = datetime.strptime(datei, '%d%m%Y').weekday()
    print(date)
    return date


def am_pm():
    tt = time.get()
    hour = datetime.strptime(tt, "%H").time()
    hourstring = str(hour)
    hourint = int(hourstring[:2])

    if hourint >= 12:
        return "PM"
    else:
        return "AM"


def check():
    if day_of_week() == 1 and am_pm() == "AM":
        print("tuesday am") #use this to get the different menu


backButton = Button(root, text="Back") #Return to parent window
backButton.grid(row=5, column=1)

confirmButton = Button(root, text="Confirm") #This button should use the output from the "day of week function+AM/PM" function, to open another window that displays different store menus
confirmButton.grid(row=5, column=3)

root.geometry("300x300")
mainloop()


