#!/bin/sh
curl --upload-file ../build/libs/romantic-spots-0.1.0.war "http://applifyed:Bitchplease@localhost:8080/manager/text/deploy?path=/romantic-spots-0.1.0&update=true"
