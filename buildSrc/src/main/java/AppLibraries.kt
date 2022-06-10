import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.*

fun DependencyHandler.applibraries() {

    androidX()
    DaggerHilt()
    glide()
    gson()
    gander()
    materialDesign()
    NavGraph()
    okHttp()
    retrofit()
    vmLifeCycle()
    androidPaging()
    testUnit()
    coroutine()
}