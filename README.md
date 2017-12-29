# Codingame with scalakit example

The goal is to be able to use a dedicated git depot for all your codingame code while being able to easily contribute
to [huiwang/codingame-scala-kit](https://github.com/huiwang/codingame-scala-kit) project.
We will use [git submodules]() to embbed codingame-scala-kit git depot into another one.

## Prerequisites

In order to be able to contribute to [huiwang/codingame-scala-kit](https://github.com/huiwang/codingame-scala-kit)
starts by forking it, my forked instance, used in the following instructions, is
[dacr/CodinGame-Scala-Kit](https://github.com/dacr/CodinGame-Scala-Kit), to be replace with you own.


## How to use this example ?

In order to fork and/or reuse this example :
```bash
$ git clone --recurse-submodules git@github.com:dacr/codingame-with-scalakit-example.git

$ cd codingame-with-scalakit-example

$ ./enhanceMyCode

```

This example is just for a quick test, do not use it, **instead follow
the following steps with your own copy of codingame-scala-kit** and not mine ;)


## How this project was created ?

All the executed steps in order to create this project are the following :

```bash
$ mkdir codingame-with-scalakit-example

$ cd codingame-with-scalakit-example

$ git init

$ touch README.md

$ mkdir -p src/main/scala src/test/scala

$ git submodule add git@github.com:dacr/CodinGame-Scala-Kit.git codingame-scala-kit-forked

$ ln -s codingame-scala-kit-forked/build.sbt .

$ ln -s codingame-scala-kit-forked/project .

$ ln -s codingame-scala-kit-forked/.gitignore .

$ ln -rs codingame-scala-kit-forked/src/main/scala/com/ src/main/scala/

$ ln -rs codingame-scala-kit-forked/src/test/scala/com/ src/test/scala/

$ mkdir -p src/main/scala/mycode src/test/scala/mycode

$ echo -e 'package mycode
import com.truelaurel.codingame.logging.CGLogger._
object MyCode { 
  def main(args:String*):Unit = {
    info("Hello world")
  }
}' > src/main/scala/mycode/MyCode.scala

$ echo -e 'package mycode
import org.scalatest._
class MyCodeTest extends FlatSpec with Matchers {
  it should "pi constants must have the right value " in {
    math.Pi shouldBe 3.14 +- 0.01
  }
}
' >src/test/scala/mycode/MyCodeTest.scala

$ sbt test

$ sbt 'test-only mycode.MyCodeTest'

$ sbt 'runMain com.truelaurel.codingame.tool.bundle.BundlerMain MyCode.scala'

$ cat target/MyCode.scala 

```


Easy way for continuous test and bundling of one practice or challenge :

```bash
$ echo -e '#!/bin/bash

BUNDLER=com.truelaurel.codingame.tool.bundle.BundlerMain
BASENAME=$(basename $0)
NAME=${BASENAME##enhance}

echo "Continuously testing and bundling $NAME"
sbt "~ ; test-only **.${NAME}Test ; runMain $BUNDLER $NAME.scala"
' > enhance

$ chmod u+x enhance

$ ln -s enhance enhanceMyCode

$ ./enhanceMyCode
```


## How can I contribute to codingame-scala-kit ?

you can enter into the submodule, the `codingame-scala-kit-forked` directory in this example,
and make any changes you want as if you were direcly inside this embedded depot.

```bash
$ cd codingame-scala-kit-forked

$ vi README.md

$ git add README.md

$ git commit -m 'Add information on how to use the scalakit as a git submodule'

$ git push origin master

$ cd ..

$ git status
On branch master
Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git checkout -- <file>..." to discard changes in working directory)

	modified:   codingame-scala-kit-forked (new commits)

$ git add codingame-scala-kit-forked

$ git commit -m "Keep in sync with codingame-scala-kit-forked latest changes"

$ git push origin master

```

And then you'll use the github web interface, and in your own copy of codinggame-scala-kit
you'll be able to create a new pull request for
[huiwang/codingame-scala-kit](https://github.com/huiwang/codingame-scala-kit)

