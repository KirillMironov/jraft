#!/bin/bash

gnome-terminal -- bash -c "java -jar ../target/jraft-0.0.0-SNAPSHOT.jar localhost:8080" &
gnome-terminal -- bash -c "java -jar ../target/jraft-0.0.0-SNAPSHOT.jar localhost:8081" &
gnome-terminal -- bash -c "java -jar ../target/jraft-0.0.0-SNAPSHOT.jar localhost:8082" &
