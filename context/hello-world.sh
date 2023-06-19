#!/bin/bash
set -eux;
shopt -s expand_aliases;
alias timestamp='date "+%Y-%m-%dT%H:%M:%S.%3N%:z"';
echo "$(timestamp) : hello world";
