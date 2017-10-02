Java Packet Manager (JPM)
===

This project is a new attempt to create a universal packet manager/installer for java applications and libraries. It utilizes build tools like maven or gradle to fetch dependencies and build executable jars.

Once binaries are installed a shell file is written to allow for simple execution of an installed command.

Install
---

To install the `hello` command provided to check if jpm works, run:

```shell
jpm install https://github.com/FelixResch/jpm-hello.git hello
```

After the installation completes and you have `~/.jpm/bin` on your `$PATH` you can run `hello` or `jpm.hello` from the command line.

Roadmap
---

- [ ] Gradle support
- [ ] Support for other maven repositories than central
- [ ] Direct maven install support
- [ ] Add support for a distribution method, that does not require maven or gradle