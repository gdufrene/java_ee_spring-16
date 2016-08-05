#!/bin/bash
sysname=$(uname -s)

function dl {
  while (( "$#" )); do
    dir="$1"
    url="$2"
    shift 2
    file="$dir/$(basename $url)"
    if [ ! -f "$file" ]; then
      if which wget ; then
        wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" -P "$dir" "$url"
      else
        curl -j -k -H "Cookie: oraclelicense=accept-securebackup-cookie" -o "$file" "$url"
      fi
    else
      echo "$file already exists."
    fi
  done
}

if [ "$sysname" = "Darwin" ] ; then
FILES="eclipse http://www.mirrorservice.org/sites/download.eclipse.org/eclipseMirror/technology/epp/downloads/release/neon/R/eclipse-jee-neon-R-macosx-cocoa-x86_64.tar.gz \
maven http://apache.crihan.fr/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz \
java http://download.oracle.com/otn-pub/java/jdk/8u102-b14/jdk-8u102-linux-x64.tar.gz \
tomcat http://mirrors.ircam.fr/pub/apache/tomcat/tomcat-8/v8.5.4/bin/apache-tomcat-8.5.4.tar.gz"
else
FILES="hello world"
fi

dl $FILES
