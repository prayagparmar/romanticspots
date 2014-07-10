#!/bin/sh
curl --upload-file ../build/libs/romantic-spots-0.1.0.war "http://applifyed:Bitchplease@ec2-54-88-158-228.compute-1.amazonaws.com:8080/manager/text/deploy?path=/romantic-spots-0.1.0&update=true"
