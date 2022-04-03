import os
import subprocess
import argparse
import re
from time import time, sleep


#APP_FOLDER = '/home/fracisco/Desktop/MicroRefact/Hotel-Booking-Management-System/evosuite-tests'


def compile_Tests(APP_FOLDER):
    totalFiles = 0
    totalDir = 0
    APP_FOLDER = APP_FOLDER + "/evosuite-tests"

    for base, dirs, files in os.walk(APP_FOLDER):
        print('Searching in : ',base)
        for directories in dirs:
            print('Directory ',directories)
            totalDir += 1
            print("NUMBER OF FILES ", len(files))
        for Files in files:
            comand = f"javac {base}/*.java"
            subprocess.call(comand,shell=True)
            totalFiles += 1


    print('Total number of files',totalFiles)
    print('Total Number of directories',totalDir)
    print('Total:',(totalDir + totalFiles))


def run_Tests(APP_FOLDER):

    totalFiles = 0
    totalDir = 0
    APP_FOLDER = APP_FOLDER + "/evosuite-tests"

    for base, dirs, files in os.walk(APP_FOLDER):
        print('Searching in : ',base)
        for directories in dirs:
            print('Directory ',directories)
            totalDir += 1
            print("NUMBER OF FILES ", len(files))
        for Files in files:
            if  not (re.search("scaffolding.class$",Files) or re.search("scaffolding.java$",Files) ):
                testClass = Files.split(".")[0]
                testClass = base + "/" + testClass
                callClass = ".".join(subtractionStr(testClass,APP_FOLDER).split("/")[1:]) 
                print(callClass)

                comand = f"java org.junit.runner.JUnitCore {callClass} >> testes.txt"
                subprocess.call(comand,shell=True)
                sleep(5)
            

def subtractionStr(string1, string2):
    index = 0
    for i, c in enumerate(string1):
        if  len(string2) -1 < i or c != string2[i] :
            return string1[i:]
        else:
            index = i
    return string1[index:]
def main():

    parser = argparse.ArgumentParser()
    parser.add_argument("--project","-p",required=True)

    args = parser.parse_args()

    compile_Tests(args.project)
    run_Tests(args.project)

main()
