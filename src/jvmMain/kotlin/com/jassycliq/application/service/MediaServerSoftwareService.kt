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

package com.jassycliq.application.service

import com.jassycliq.application.db.MediaServers
import com.jassycliq.application.db.dbQuery
import com.jassycliq.application.model.MediaServer
import com.jassycliq.application.model.MediaServerScheme
import com.jassycliq.application.model.MediaServerSoftware
import com.jassycliq.application.model.NewMediaServer
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class MediaServerService {

    suspend fun addMediaServer(newMediaServer: NewMediaServer): MediaServer {
        var key: EntityID<Int>? = null
        dbQuery {
            key = MediaServers.insertAndGetId {
                it[name] = newMediaServer.name
                it[software] = newMediaServer.software.name
                it[scheme] = newMediaServer.scheme.name
                it[url] = newMediaServer.url
            }
        }
        return key?.let { getMediaServer(it) }!!
    }

    suspend fun getMediaServer(id: EntityID<Int>): MediaServer? = dbQuery {
        MediaServers.select {
            (MediaServers.id eq id)
        }.mapNotNull { toMediaServer(it) }
            .singleOrNull()
    }

    suspend fun getAllMediaServers(): List<MediaServer> = dbQuery {
        MediaServers.selectAll().map { toMediaServer(it) }
    }

    private fun toMediaServer(row: ResultRow): MediaServer =
        MediaServer(
            id = row[MediaServers.id].value,
            name = row[MediaServers.name],
            software = MediaServerSoftware.valueOf(row[MediaServers.software]),
            scheme = MediaServerScheme.valueOf(row[MediaServers.scheme]),
            url = row[MediaServers.url],
        )
}
