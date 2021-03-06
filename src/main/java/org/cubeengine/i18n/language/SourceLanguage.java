/*
 * The MIT License
 * Copyright © 2013 Cube Island
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.cubeengine.i18n.language;

import org.cubeengine.i18n.plural.NotOneExpr;
import org.cubeengine.i18n.plural.PluralExpr;
import org.cubeengine.i18n.translation.TranslationContainer;

import java.util.Locale;

/**
 * This class represents the source language.
 */
public final class SourceLanguage extends NormalLanguage
{
    public static final SourceLanguage EN_US = new SourceLanguage(Locale.US, "English", 2, new NotOneExpr());

    public SourceLanguage(Locale locale, String name, int pluralCount, PluralExpr pluralExpression)
    {
        this(locale, name, name, pluralCount, pluralExpression);
    }

    public SourceLanguage(Locale locale, String name, String localName, int pluralCount, PluralExpr pluralExpression)
    {
        super(new SourceLanguageDefinition(locale, name, localName, pluralCount, pluralExpression), new TranslationContainer(), null);
    }

    public String getTranslation(String singular)
    {
        String translation = super.getTranslation(singular);
        if (translation == null)
        {
            translation = singular;
            // TODO preprocessor
            messages.putSingular(singular, translation);
        }
        return translation;
    }

    public String getTranslation(String singular, String plural, int n)
    {
        int index = getIndex(n);
        if (index == 0)
        {
            return getTranslation(singular);
        }
        String translation = super.getTranslation(singular, plural, n);
        if (translation == null)
        {
            translation = plural;
            // TODO preprocessor
            messages.putPlural(plural, translation, index - 1, this.definition.getPluralCount());
        }
        return translation;
    }

    public TranslationContainer getMessages()
    {
        return this.messages;
    }

    public Language getParent()
    {
        return null;
    }

    public LanguageDefinition getLanguageDefinition()
    {
        return this.definition;
    }

    private static class SourceLanguageDefinition implements LanguageDefinition
    {
        private static final Locale[] NO_CLONES = new Locale[0];

        private Locale locale;
        private String name;
        private String localName;
        private int pluralCount;
        private PluralExpr pluralExpression;

        private SourceLanguageDefinition(Locale locale, String name, String localName, int pluralCount, PluralExpr pluralExpression)
        {
            this.locale = locale;
            this.name = name;
            this.localName = localName;
            this.pluralCount = pluralCount;
            this.pluralExpression = pluralExpression;
        }

        public Locale getLocale()
        {
            return this.locale;
        }

        public String getName()
        {
            return this.name;
        }

        public String getLocalName()
        {
            return this.localName;
        }

        public Locale getParent()
        {
            return null;
        }

        public Locale[] getClones()
        {
            return NO_CLONES;
        }

        public int getPluralCount()
        {
            return pluralCount;
        }

        public PluralExpr getPluralExpression()
        {
            return pluralExpression;
        }
    }
}
