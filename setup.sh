path="$PWD"

sudo apt update -y 


sudo apt install software-properties-common -y
sudo add-apt-repository ppa:deadsnakes/ppa -y
sudo apt install python3.7 python3-pip python3-matplotlib  -y


cd "$path"
# Install Maven and build 
cd app/javaParser
sudo apt install maven -y
mvn package 