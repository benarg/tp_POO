JFLAGS = -g
JC = javac
SRC = /src

CLASSES = $(SRC/*.java)

all: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class