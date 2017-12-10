JAVAC=javac

JAR_NAME=group1.jar
sources=$(wildcard ems/*.java)
#classes=$(sources:.java=.class)

#vpath %.class bin
#vpath %.java src/ems


new: 
	mkdir -pv bin

clean: 
	rm -rf bin $(JAR_NAME) 

build: 
	$(JAVAC) -d bin src/ems/*.java

rebuild: clean new build

jar: $(classes)
	jar cvmf META-INF/MANIFEST.MF $(JAR_NAME) -C bin . 


