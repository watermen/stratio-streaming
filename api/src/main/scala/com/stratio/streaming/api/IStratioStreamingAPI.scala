/**
 * Copyright (C) 2014 Stratio (http://stratio.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.streaming.api

import _root_.kafka.consumer.KafkaStream
import com.stratio.streaming.commons.streams.StratioStream
import java.util.List
import com.stratio.streaming.api.messaging.{ ColumnNameValue, ColumnNameType }
import com.stratio.streaming.commons.exceptions._
import com.stratio.streaming.dto.StratioQueryStream
import com.stratio.streaming.commons.messages.{ StratioStreamingMessage, ColumnNameTypeValue }
import com.stratio.streaming.dto.StratioQueryStream

trait IStratioStreamingAPI {
  /**
   * Initializes the StratioStreamingAPI instance.
   * @return
   */
  @throws(classOf[StratioEngineConnectionException])
  def initialize(): IStratioStreamingAPI

  /**
   * /**
   * Initializes the StratioStreamingAPI instance.
   * @return
   * */
   * @param kafkaServer
   * @param kafkaPort
   * @param theZookeeperServer
   * @param theZookeeperPort
   * @return
   */
  @throws(classOf[StratioEngineConnectionException])
  def initializeWithServerConfig(kafkaServer: String,
    kafkaPort: Int,
    theZookeeperServer: String,
    theZookeeperPort: Int): IStratioStreamingAPI

  /**
   * Creates a new stream.
   * @param streamName
   * @param columns
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def createStream(streamName: String, columns: List[ColumnNameType])

  /**
   * Adds columns to a stream.
   * @param streamName
   * @param columns
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def alterStream(streamName: String, columns: List[ColumnNameType])

  /**
   * Inserts new data into a stream.
   * @param streamName
   * @param data
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  def insertData(streamName: String, data: List[ColumnNameValue])

  /**
   * Adds a query to a stream.
   * @param streamName
   * @param query
   * @return the query Id
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def addQuery(streamName: String, query: String): String

  /**
   * Removes a query from a stream.
   * @param streamName
   * @param queryId
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioEngineOperationException])
  def removeQuery(streamName: String, queryId: String)

  /**
   * Removes a stream
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  @throws(classOf[StratioEngineOperationException])
  def dropStream(streamName: String)

  /**
   * Starts listening to a stream.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  def listenStream(streamName: String): KafkaStream[String, StratioStreamingMessage]

  /**
   * Stops listening to a stream.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPISecurityException])
  def stopListenStream(streamName: String)

  /**
   * Gets a list of the columns from a given stream.
   * @param stream
   * @throws com.stratio.streaming.commons.exceptions.StratioEngineOperationException
   * @return
   */
  @throws(classOf[StratioEngineOperationException])
  def columnsFromStream(stream: String): List[ColumnNameTypeValue]

  /**
   *  Gets a list of the queries from a given stream.
   * @param stream
   * @throws com.stratio.streaming.commons.exceptions.StratioEngineOperationException
   * @return
   */
  @throws(classOf[StratioEngineOperationException])
  def queriesFromStream(stream: String): List[StratioQueryStream]

  /**
   * Gets a list of all the stream that currently exists.
   * @return a list with the streams
   */
  @throws(classOf[StratioEngineStatusException])
  @throws(classOf[StratioAPIGenericException])
  def listStreams(): List[StratioStream]

  /**
   * Indexes the stream to the elasticsearch instance.
   */
  @throws(classOf[StratioEngineStatusException])
  def indexStream(stream: String)

  /**
   * Stops indexing the stream.
   */
  @throws(classOf[StratioEngineStatusException])
  def stopIndexStream(stream: String)

  /**
   * Saves the stream to cassandra DB.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  def saveToCassandra(streamName: String)

  /**
   * Stops saving the stream to cassandra DB.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  def stopSaveToCassandra(streamName: String)

  /**
   * Saves the stream to MongoDB.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  def saveToMongo(streamName: String)

  /**
   * Stops saving the stream to MongoDB.
   * @param streamName
   */
  @throws(classOf[StratioEngineStatusException])
  def stopSaveToMongo(streamName: String)

  /**
   * Allows the client to define the time that the API
   * will wait for the engine responses.
   *
   * @param timeOutInMs
   * @throws com.stratio.streaming.commons.exceptions.StratioEngineStatusException
   */
  @throws(classOf[StratioEngineStatusException])
  def defineAcknowledgeTimeOut(timeOutInMs: Int): IStratioStreamingAPI
}
