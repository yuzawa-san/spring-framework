/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.http;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

/**
 * {@code HttpHeaders} object that can only be read, not written to.
 *
 * @author Brian Clozel
 * @author Sam Brannen
 * @since 5.1.1
 */
class ReadOnlyHttpHeaders extends HttpHeaders {

	private static final long serialVersionUID = -8578554704772377436L;

	final MultiValueMap<String, String> originalHeaders;

	@Nullable
	private MediaType cachedContentType;

	@Nullable
	@SuppressWarnings("serial")
	private List<MediaType> cachedAccept;


	ReadOnlyHttpHeaders(MultiValueMap<String, String> headers) {
		super(CollectionUtils.unmodifiableMultiValueMap(headers));
		this.originalHeaders = headers;
	}


	@Override
	public MediaType getContentType() {
		if (this.cachedContentType != null) {
			return this.cachedContentType;
		}
		else {
			MediaType contentType = super.getContentType();
			this.cachedContentType = contentType;
			return contentType;
		}
	}

	@Override
	public List<MediaType> getAccept() {
		if (this.cachedAccept != null) {
			return this.cachedAccept;
		}
		else {
			List<MediaType> accept = super.getAccept();
			this.cachedAccept = accept;
			return accept;
		}
	}

	@Override
	public void clearContentHeaders() {
		// No-op.
	}

}
