# ETLSimilarity

Computes the similarity of ETL files. Actually,
the current implementation computes the similarity
of XML file using the Phoenix tool (https://github.com/dew-uff/phoenix).

### Requirements

   * Java 8 (if it is necessary to build the Phoenix tool)
   * Java 11 (or above)
   * Python3
   
You might benefit from using sdkman to keep both Java versions. 

#### Execution

To execute the program, edit the file `driver.py` to specify the 
folder with the ETL files. Due to a Phoenix limitation, 
the file extensions must bem `xml`. After that, execute

```shell
$ python3 driver.py
```

Depending on the total number of files to be compared with each other, the 
execution process might last several hours. 

### Build de Project

You need to first install our specific Phoenix  version
(available at https://github.com/rbonifacio/phoenix). Just
clone the repository and run `mvn install`. Note, you
might need Java 8 to build Phoenix.

After that, build and assembly this project as usual (`mvn clean assemply:only`),
and copy the generated artifact to `program.jar`. This simplifies the
execution process. Later we can improve the usability of this
tool.