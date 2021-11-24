import os
class Settings:
    PROJECT_NAME = ''
    PROJECT_PATH = ''
    PROJECT_PATH_MS =''
    ORIGINAL_PROJECT_PATH = ''
    DIRECTORY = os.path.abspath(os.getcwd())
    PrimitiveTypes = ["byte","short","int","Integer","long","Long","float","double","boolean","String","char","void"]