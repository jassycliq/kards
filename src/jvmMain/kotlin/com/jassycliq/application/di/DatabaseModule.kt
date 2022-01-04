/*
 * MIT License
 *
 * Copyright (c)  Jose Salgado
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.jassycliq.application.di

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import javax.sql.DataSource

private fun runFlyway(datasource: DataSource) {
    val flyway = Flyway.configure()
        .dataSource(datasource)
        .load()
    try {
        flyway.info()
        flyway.migrate()
    } catch (e: Exception) {
//        log.error("Exception running flyway migration", e)
        throw e
    }
//    log.info("Flyway migration has finished")
}

private fun config(configDir: String) = HikariConfig().apply {
    jdbcUrl         = "jdbc:h2:$configDir/db/kardsDb"
    driverClassName = "org.h2.Driver"
    username        = "kards"
    password        = "kards"
    maximumPoolSize = 10
}

private fun dataSource(configDir: String) = HikariDataSource(config(configDir))

private fun db(configDir: String) {
    Database.connect(dataSource(configDir))
    runFlyway(dataSource(configDir))
}

val databaseModule = module { single(createdAtStart=true) { db(getProperty("config_dir")) } }
