import time

# def displaymenu(store):
#     itemlist=[]
#     menu=list(store.items.keys())
#     prices=list(store.items.values())
#     for i in range(len(store.items)):
#         itemlist.append("{}\t${:0.2f}".format(menu[i],prices[i]).expandtabs(25))
#     items='\n'.join(itemlist)
#     return items

def displaymenu(store):
    itemlist=[]
    menu=list(store.items.keys())
    for i in range(len(store.items)):
        itemlist.append((menu[i]))
    items='\n'.join(itemlist)
    return items


def displayprice(store):
    itemlist=[]
    prices=list(store.items.values())
    for i in range(len(store.items)):
        itemlist.append("${:0.2f}".format(prices[i]))
    items='\n'.join(itemlist)
    return items
                   
def currenttime():
    totalseconds=time.time()
    timeoffset = 8 * 3600
    totalseconds += timeoffset
    dtime=time.gmtime(totalseconds)
    print(dtime)
    return dtime

def numlist(num):
    nlist=[]
    for i in range(num):
        if (i+1)<10:
            nlist.append(str.format('0{}',i+1))
        else:
            nlist.append(str.format('{}',i+1))
    return nlist

def timelist(num):
    nlist=[]
    for i in range(num):
        if (i)<10:
            nlist.append(str.format('0{}',i))
        else:
            nlist.append(str.format('{}',i))
    return nlist


def usertime(dd,mm,yyyy,hour,minute):
    dtime=time.strptime(str(dd)+str(mm)+str(yyyy)+str(hour)+str(minute),'%d%m%Y%H%M')
    return dtime

def timebmenu(amenu,aprices,store):
    if bool(amenu[store.index])==False:
        return
    else:
            store.menu=amenu[store.index]
            store.prices=aprices[store.index]
            store.update()
            return

def waittime(pax):
    return (pax*3)

def ophrsoutput(store):
    return "Weekdays:   {}\nWeekends:   {}".format(store.ophours[0],store.ophours[1])

def checkoperating(dtime,stores):
    print(dtime.tm_hour)
    now=dtime.tm_hour*100+dtime.tm_min
    # check=[]
    currentopstores = []
    for F in stores:
        #If current date or user inputted date is a weekday
        if dtime.tm_wday <= 4:
            #Checks weekday operating hours of store and returns True if store is open or False if closed.
            if now >= int(stores[F].ophours[0][0:4]) and now <= int(stores[F].ophours[0][7:11]):
                #check.append(True)
                currentopstores.append(stores[F])
            # else:
            #     check.append(False)
        #If current date or user inputted date is a weekend
        else:
            #Checks weekend operating hours of store and returns True if store is open or False if closed.
            if now >= int(stores[F].ophours[1][0:4]) and now <= int(stores[F].ophours[1][7:11]):
                #check.append(True)
                currentopstores.append(stores[F])
            # else:
            #     check.append(False)
    return currentopstores

