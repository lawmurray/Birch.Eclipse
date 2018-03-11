# The Birch Eclipse Plugin

The Birch Eclipse Plugin provides a syntax-highlighting text editor for the [Eclipse](http://www.eclipse.org) IDE.


## Installation

An update site is available at <http://www.birch-lang.org/eclipse/updates>.

From within Eclipse, use *Help > Install New Software...*. Click the *Add...* button and enter this URL. Follow the prompts from there. The plugin should be automatically associated with the `*.bi` file extension.


## Creating Birch projects in Eclipse

Once a Birch project has been created from the command line with `birch init`:

  1. Import it into Eclipse using *File > New > Makefile Project with Existing Code*.
  2. Under *Project > Properties*, select *C/C++ Build* on the left.
  3. Go to the *Builder Settings* tab and enter `birch` as the build command.
  4. Go to the *Behavior* tab and enter `build` (or `install`, if preferred) next to the *Build (Incremental build)* checkbox.


## Installation for plugin developers

To install the plugin, first ensure that your Eclipse environment has the appropriate components. Use *Help > Install New Software...*, and install:

  * Eclipse Java Development Tools
  * Eclipse Plug-in Development Environment

Import the `Birch.Eclipse` project:

  1. *File > Import... > Git > Projects from Git > Clone URI*.
  2. Enter the URI: https://github.com/lawmurray/Birch.Eclipse.git.
  3. *Next*, then *Next* again, the branch *master* should be checked.
  4. Set the local directory to which to clone; consider changing `.../git/...` to `.../workspace/...`.
  5. *Next* again.
  6. *Import existing Eclipse projects*.
  7. *Finish*.

Repeat to import the *Birch.EclipseFeature* and *Birch.EclipseSite* projects, which are required to deploy the plugin to the update site for users.

The project should compile automatically, otherwise use `Project > Build Project`.

To install:

  1. *File > Export... > Plug-in Development > Deployable plug-ins and fragments*.
  2. Check the box against *Birch Editor*.
  3. Select *Install into host*.

After installation, it is possible to create the update site. Open the `site.xml` file of the `Birch.EclipseSite` project and click the *Build All* button.

To change the version number, it is necessary to modify the `feature.xml` file under the *Birch.EclipseFeature* project.
