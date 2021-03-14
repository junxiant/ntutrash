import storeDict
def retrieveStoreList():
    try:
        stores = storeDict.stores
        storelist = []
        for i in stores["stores"]:
                storelist.append(i)
        return storelist
    except: 
        print("ERROR, FILE NOT FOUND")
        return ["Database file not found"]
def retrieveMenuByStore(store):
        stores = storeDict.stores
        menuList = []
        try:
            for i in stores['stores'][store]['menu']:
                menuList.append((i + "\t" + "${:0.2f}".format(stores['stores'][store]['menu'].get(i))).expandtabs(25))
            menustring = '\n'.join(menuList)
        except:
            menustring = "-No Items-"
        return menustring


def retrieveMenuImageByStore(store):
    stores = storeDict.stores
    img = stores["stores"][store]["img"]
    return img


# def retrievePriceListByKey(key):
#     try:
#         stores = storeDict.stores
#         storelist = []
#         for i in stores["stores"]:
#             for keys in i:
#                 print(keys)
#                 storelist.append(keys)
#         return storelist
#     except:
#         print("ERROR, FILE NOT FOUND")
#         return ["Database file not found"]
