package ar.edu.unq.desapp.grupof.backenddesappapi

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.library.Architectures
import org.junit.jupiter.api.Test
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RestController

class ArchitectureTests {
    @Test
    fun serviceAnnotation() {
        val ownerClasses = ClassFileImporter().withImportOption(ImportOption.DoNotIncludeTests())
            .importPackages("ar.edu.unq.desapp.grupof.backenddesappapi")
        val rule: ArchRule = ArchRuleDefinition.classes()
            .that().resideInAPackage("..services..")
            .should().haveSimpleNameEndingWith("Service")
            .andShould().beAnnotatedWith(Service::class.java)
        rule.check(ownerClasses)
    }

    @Test
    fun controllerAnnotation() {
        val ownerClasses = ClassFileImporter().withImportOption(ImportOption.DoNotIncludeTests())
            .importPackages("ar.edu.unq.desapp.grupof.backenddesappapi")
        val rule: ArchRule = ArchRuleDefinition.classes()
            .that().resideInAPackage("..controllers..")
            .should().haveSimpleNameEndingWith("Controller")
            .andShould().beAnnotatedWith(RestController::class.java)
        rule.check(ownerClasses)
    }

    @Test
    fun repositoryAnnotation() {
        val ownerClasses = ClassFileImporter().withImportOption(ImportOption.DoNotIncludeTests())
            .importPackages("ar.edu.unq.desapp.grupof.backenddesappapi")
        val rule: ArchRule = ArchRuleDefinition.classes()
            .that().resideInAPackage("..repositories..")
            .should().haveSimpleNameEndingWith("Repository")
            .andShould().beAnnotatedWith(Repository::class.java)
        rule.check(ownerClasses)
    }

    @Test
    fun controllersDepends() {
        val ownerClasses = ClassFileImporter().withImportOption(ImportOption.DoNotIncludeTests())
            .importPackages("ar.edu.unq.desapp.grupof.backenddesappapi")
        val arch = Architectures.layeredArchitecture() // Define layers
            .layer("Controller").definedBy("..controllers..")
            .layer("Service").definedBy("..services..")
            .layer("Repository").definedBy("..repositories..") // Add constraints
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
        assert(ownerClasses.isNotEmpty())
        arch.check(ownerClasses)
    }
}