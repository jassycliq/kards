/*
 * MIT License
 *
 * Copyright (c) 2022 Jose Salgado
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

package com.jassycliq.application.data.mediaserver

import com.jassycliq.application.model.MediaServer
import com.jassycliq.application.model.MediaServerScheme
import com.jassycliq.application.model.MediaServerSoftware
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object MediaServers : IntIdTable() {
    val name = varchar("name", 255)
    val software = varchar("software", 32)
    val scheme = varchar("scheme", 5)
    val url = varchar("url", 255)
}

fun toMediaServer(row: ResultRow): MediaServer =
    MediaServer(
        id = row[MediaServers.id].value,
        name = row[MediaServers.name],
        software = MediaServerSoftware.valueOf(row[MediaServers.software]),
        scheme = MediaServerScheme.valueOf(row[MediaServers.scheme]),
        url = row[MediaServers.url],
    )