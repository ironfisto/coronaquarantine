package services.database

import com.datastax.driver.dse.DseCluster
import com.datastax.driver.dse.graph.{GraphOptions, GraphProtocol, SimpleGraphStatement}
import com.datastax.dse.graph.internal.DseRemoteConnection
import com.google.inject.Singleton
import gremlin.scala._
import javax.inject.Inject
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph
import play.api.Configuration

@Singleton
class DatabaseConnection @Inject()(config: Configuration) {

  private val dseCluster = DseCluster
    .builder()
    .addContactPoints(
      "dbip"
    )
    .withPort("dbport")
    .build()

  private val graphName: String = "geo"
  private val graphOptions = new GraphOptions()
  graphOptions.setGraphName(graphName)
  graphOptions.setGraphSubProtocol(GraphProtocol.GRAPHSON_2_0)
  private val session = dseCluster.connect()


  session.executeGraph(
    new SimpleGraphStatement(
      "schema.config().option('graph.schema_mode').set('development')")
      .setGraphName(graphName))
  session.executeGraph(
    new SimpleGraphStatement(
      "schema.config().option('graph.allow_scan').set('true')")
      .setGraphName(graphName))
  // Create a ScalaGraph from a remote Traversal Source using withRemote
  // See: http://tinkerpop.apache.org/docs/current/reference/#connecting-via-remotegraph for more details
  private val connection =
  DseRemoteConnection.builder(session).withGraphOptions(graphOptions).build()

  val g: ScalaGraph = EmptyGraph
    .instance()
    .asScala
    .configure(_.withRemote(connection))

}
