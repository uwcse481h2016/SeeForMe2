#!/bin/bash
base64 -i $1.jpg -o $1base.txt
cat a.txt $1base.txt b.txt > $1.json
curl -v -k -s -H "Content-Type: application/json" https://vision.googleapis.com/v1alpha1/images:annotate?key=AIzaSyCBHJTfMQp6ZqmnP-tZagRhlxRppBzPDWw --data-binary @$1.json