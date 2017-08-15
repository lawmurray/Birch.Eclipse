# The Birch Eclipse Plugin

The Birch Eclipse Plugin provides a syntax-highlighting text editor for the Eclipse IDE. 

An Eclipse installation site has yet to be set up for the plugin. In the meantime, it is available in a separate `Birch.Eclipse` repository.

To install the plugin, first ensure that your Eclipse environment has the appropriate components. Use *Help > Install New Software...*, and install:

  * Eclipse Java Development Tools
  * Eclipse Plug-in Development Environment

Import the `Birch.Eclipse` project:

  1. *File > Import... > Git > Projects from Git > Clone URI*.
  2. Enter the URI: https://github.com/lawmurray/Birch.Eclipse.git.
  3. *Next*, then *Next* again, the branch *master* should be checked.
  4. Set the local directory to clone to, we suggest changing `.../git/...` to `.../workspace/...`.
  5. *Next* again.
  6. *Import existing Eclipse projects*.
  7. *Finish*.

The project should compile automatically, otherwise use `Project > Build Project`.

To install:

  1. *File > Export... > Plug-in Development > Deployable plug-ins and fragments*.
  2. Check the box against *Birch (x.y.z)*.
  3. Select *Install into host*.
