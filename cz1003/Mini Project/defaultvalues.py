'''
Default Values File

Contains Required Parameters for Canteen Menu System.

names: List of stores names.

amenu: 2D List of AM Menus of Stores (If Applicable) corresponding to store order in names.

aprices: 2D List of Prices of AM Menu Items corresponding to AM Menu.

defmenu: 2D List of Default Menu items corresponding to store order in names.

defprices: 2D Prices of Menu Items corresponding to Default Menu.

ophours: 2D List of Opening hours of stores in 24h format.
         First index corresponds to weekday while Seconds corresponds to weekend operating hours.
'''

names=["Macdonalds",
        "KFC",
        "Pizza Hut",
        "Long Johns Silver",
        "Subway",
        "Starbucks"]

amenu=[["Pancakes","Egg McMuffin","Hash Brown"],
        ["Chicken Porridge","Pancakes Platter"],
        [],
        [],
        [],
        []]

aprices=[[4.00,4.50,2.50],
         [3.00,5.80],
         [],
         [],
         [],
         []]


#Default Menu loaded; If am menu exists, this becomes the pm menu.
defmenu=[["Fillet-o-Fish","Big Mac","Fries","Apple Pie"],
       ["1pc Fried Chicken","Mashed Potatoes","Coleslaw"],
       ["Hawaiian","Meat Galore","Chicken Drumlets"],
       ["Today's Catch","Fish and Chicken","Fish","Chicken"],
       ["Subway Melt","Chicken Teriyaki","Chicken Bacon Ranch"],
       ["Coffee Tall","Coffee Grande","Coffee Venti","Coffee Trenta"]]
       


defprices=[[3.00,5.00,2.50,1.50],
        [4.00,2.50,2.50],
        [10.00,12.00,6.00],
        [5.50,7.50,4.50,4.50],
        [6.50,7.50,6.90],
        [2.25,2.65,2.95,3.45]]

ophours=[["0800 - 2100","0900 - 1900"],
         ["0800 - 2000","0800 - 2000"],
         ["0800 - 1800","0900 - 1500"],
         ["0930 - 2159","1200 - 2100"],
         ["0830 - 2100","0930 - 1700"],
         ["0830 - 2130","0900 - 1930"]]

months=["January","February","March","April","May","June",
        "July","August","September","October","November","December"]

class stores:
    def __init__(self,index,names,menu,prices,ophours):       
        self.index=index
        self.name=names[index]
        self.menu=menu[index]
        self.prices=prices[index]
        self.ophours=ophours[index]
        self.images={"header":"img/{}/header.png".format(self.name)}
        self.foodimages1 = {"1": "img/{}/1.png".format(self.name)}
        self.foodimages2 = {"2": "img/{}/2.png".format(self.name)}
        # count = 1
        # while True:
        #     try:
        #         self.images[count] = "img/{}/{}.png".format(self.name,count)
        #         count +=1
        #     except:
        #         break

        self.update()
    def add(self,menu,prices):
        for i in range(len(self.menu)):
            self.items[self.menu[i]]=prices[i]
    def remove(self,bar):
        for i in range(len(bar)):
            try:
                del self.items[bar[i]]
            except KeyError:
                print("Key {} not found".format(bar[i]))
    def update(self):
        self.items={}
        for i in range(len(self.menu)):
            self.items[self.menu[i]]=self.prices[i]