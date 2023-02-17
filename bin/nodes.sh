#!/bin/bash

jar="../target/jraft-0.0.0-SNAPSHOT.jar"
peers="localhost:8080,localhost:8081,localhost:8082"

gnome-terminal -- bash -c "java -jar $jar localhost:8080 $peers"
gnome-terminal -- bash -c "java -jar $jar localhost:8081 $peers"
gnome-terminal -- bash -c "java -jar $jar localhost:8082 $peers"
