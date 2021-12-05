package system.utils;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.io.File;
import java.util.EnumSet;

// Hibernate 5
public class InitScheme {

    public static final String SCRIPT_FILE = "script.sql";

    private static SchemaExport getSchemaExport() {

        SchemaExport export = new SchemaExport();
        // Script file
        File outputFile = new File(SCRIPT_FILE);
        String outputFilePath = outputFile.getAbsolutePath();

        System.out.println("Export file: " + outputFilePath);

        export.setDelimiter(";");
        export.setOutputFile(outputFilePath);

        // No stop if error
        export.setHaltOnError(false);
        //
        return export;
    }

    public static void dropDataBase(SchemaExport export, Metadata metadata) {

        // TargetType.DATABASE - Init into Database
        // TargetType.SCRIPT - Export into file Script.
        // TargetType.STDOUT - Show log
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

        export.drop(targetTypes, metadata);
    }

    public static void createDataBase(SchemaExport export, Metadata metadata) {

        // TargetType.DATABASE - Init into Database
        // TargetType.SCRIPT - Export into file Script.
        // TargetType.STDOUT - Show log
        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);

        SchemaExport.Action action = SchemaExport.Action.CREATE;
        //
        export.execute(targetTypes, action, metadata);

        System.out.println("Export OK");

    }

    public static void main(String[] args) {
        // Config file
        String configFileName = "\\META-INF\\hibernate.cfg.xml";

        // Create ServiceRegistry from hibernate.cfg.xml
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
                .configure(configFileName).build();

        // Create Metadata from ServiceRegistry
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        SchemaExport export = getSchemaExport();

        System.out.println("Drop Database...");
        // Drop Database
        dropDataBase(export, metadata);

        System.out.println("Create Database...");

        // Create schema again
        createDataBase(export, metadata);
    }

}