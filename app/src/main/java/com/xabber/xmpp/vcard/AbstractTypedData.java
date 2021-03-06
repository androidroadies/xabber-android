/**
 * Copyright (c) 2013, Redsolution LTD. All rights reserved.
 *
 * This file is part of Xabber project; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.
 *
 * Xabber is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * along with this program. If not, see http://www.gnu.org/licenses/.
 */
package com.xabber.xmpp.vcard;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.xmlpull.v1.XmlSerializer;

import com.xabber.xmpp.Instance;

public abstract class AbstractTypedData<Type extends Enum<?>> implements
        Instance {

    protected final Set<Type> types;

    public AbstractTypedData() {
        types = new HashSet<Type>();
    }

    @Override
    public void serialize(XmlSerializer serializer) throws IOException {
        serializer.startTag(null, getElementName());
        for (Type type : types) {
            serializer.startTag(null, type.toString());
            serializer.endTag(null, type.toString());
        }
        writeBody(serializer);
        serializer.endTag(null, getElementName());
    }

    protected abstract String getElementName();

    protected abstract void writeBody(XmlSerializer serializer)
            throws IOException;

    public Set<Type> getTypes() {
        return types;
    }

}
