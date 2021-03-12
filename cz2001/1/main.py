def solve(string, substring):
    n = 0
    current = 0
    arr = []

    # See which index can we continue to count from
    for i in substring[int(len(substring)/2):]:
        if i == substring[n]:
            n += 1
        else:
            n = 0

    total_repeated_length = 0  # Total length of repetitions
    repeated_length = 0  # Number of elements in 1 repetition

    # Find the length of the shortest repetition and how long it goes for

    for i in range(1, len(substring)):
        if substring[repeated_length] == substring[i]:
            repeated_length += 1
            if repeated_length == (i + 1) / 2:
                total_repeated_length = i + 1
                p = 0
                for j in range(i + 1, len(substring)):
                    if substring[j] == substring[p]:
                        p += 1
                        if p >= repeated_length:
                            total_repeated_length += repeated_length
                            p = 0
                    else:
                        break
                break
        else:
            repeated_length = 0

    # Iterate through the string

    for i in range(len(string)):
        if substring[current] == string[i]:
            current += 1
            if current >= len(substring):
                arr.append(i - current + 2)
                current = n
        else:
            if current == total_repeated_length and string[i] == substring[0]:
                current = current - repeated_length + 1
            elif substring[0] == string[i]:
                current = 1
            else:
                current = 0

    return arr


if __name__ == '__main__':
    string = input("Please enter a string (enter 0 to quit):\n")
    while string != "0":
        substring = input("Please enter a substring:\n")
        arr = solve(string, substring)
        if len(arr) == 0:
            print("Empty")
        else:
            print(arr)
        string = input("Please enter a string (enter 0 to quit):\n")
