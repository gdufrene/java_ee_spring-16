#!/bin/bash

function gen() {
  cat tpl/header.html tpl/$1.html tpl/footer.html > $1.html
}

gen index
