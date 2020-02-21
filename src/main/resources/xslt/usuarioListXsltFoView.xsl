<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
    <xsl:variable name="organismo-nivel1-nombre" select="/usuario-list/params-config/param-config[name='organismo.nivel.1.nombre']/value/text()"/>
    <xsl:variable name="organismo-nivel1-via-nombre" select="/usuario-list/params-config/param-config[name='organismo.nivel.1.via.nombre']/value/text()"/>
    <xsl:variable name="organismo-nivel1-via-cp" select="/usuario-list/params-config/param-config[name='organismo.nivel.1.via.cp']/value/text()"/>
    <xsl:variable name="organismo-nivel1-via-provincia" select="/usuario-list/params-config/param-config[name='organismo.nivel.1.via.provincia']/value/text()"/>
    <xsl:variable name="organismo-nivel1-telefono" select="/usuario-list/params-config/param-config[name='organismo.nivel.1.telefono']/value/text()"/>
    <xsl:variable name="organismo-nivel1-fax" select="/usuario-list/params-config/param-config[name='organismo.nivel.1.fax']/value/text()"/>
    <xsl:variable name="organismo-nivel2-nombre" select="/usuario-list/params-config/param-config[name='organismo.nivel.2.nombre']/value/text()"/>
    <xsl:variable name="organismo-nivel3-nombre" select="/usuario-list/params-config/param-config[name='organismo.nivel.3.nombre']/value/text()"/>
    
    <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
    <xsl:template match="usuario-list">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4"
                                       page-width="21cm" page-height="29.7cm"
                                       margin-top="1.0cm" margin-bottom="1.0cm"
                                       margin-left="2.0cm" margin-right="2.0cm">
                    <fo:region-body margin-top="3.0cm" margin-bottom="1.5cm"/>
                    <fo:region-before extent="2.0cm"/>
                    <fo:region-after extent="1.0cm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
				
            <fo:page-sequence master-reference="A4" initial-page-number="1">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:table table-layout="fixed" border-collapse="collapse" width="100%">
                        <fo:table-column column-width="2.0cm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="3.8cm"/>
                        <fo:table-body>
                            <fo:table-row display-align="center">
                                <fo:table-cell>
                                    <fo:block>
                                        <!--content-width="80%" content-height="80%"-->
                                        <fo:external-graphic src="escudo.gif" height="2.0cm" content-width="scale-to-fit" content-height="scale-to-fit"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-left="0.2cm" font-size="8pt">
                                    <xsl:call-template name="tokenize">
                                        <xsl:with-param name="cadena" select="$organismo-nivel1-nombre"/>
					<xsl:with-param name="separador" select="'\n'"/>
                                    </xsl:call-template>
                                </fo:table-cell>
                                <fo:table-cell padding-left="0.2cm" padding-top="0.2cm" width="3.8cm" font-size="6pt">
                                    <fo:block background-color="#ECECEC" padding-left="0.2cm" padding-top="0.2cm" padding-bottom="0.2cm"><xsl:copy-of select="$organismo-nivel2-nombre"/></fo:block>
                                    <fo:block padding-left="0.2cm" padding-top="0.2cm"><xsl:copy-of select="$organismo-nivel3-nombre"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:static-content>
            	
                <fo:static-content flow-name="xsl-region-after">
                    <fo:table table-layout="fixed" border-collapse="collapse" width="100%" font-size="6pt">
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="3.8cm"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>Página <fo:page-number/></fo:block>
                                </fo:table-cell>
                                <fo:table-cell width="3.8cm" padding-left="0.2cm" border-left="0.02cm solid #000000">
                                    <fo:block>
                                        <xsl:copy-of select="$organismo-nivel1-via-nombre"/>
                                    </fo:block>
                                    <fo:block>
                                        <xsl:copy-of select="$organismo-nivel1-via-cp"/>
                                        <xsl:text> </xsl:text>
                                        <xsl:copy-of select="$organismo-nivel1-via-provincia"/>
                                    </fo:block>
                                    <fo:block>TEL.: <xsl:copy-of select="$organismo-nivel1-telefono"/></fo:block>
                                    <fo:block>FAX: <xsl:copy-of select="$organismo-nivel1-fax"/></fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:static-content>
				
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-weight="bold" font-size="10pt" space-after="0.5cm">LISTADO DE USUARIOS</fo:block>
                   			
                    <fo:block font-weight="bold" font-size="8pt" space-after="0.2cm">Criterios de Búsqueda</fo:block>
                    <xsl:apply-templates select="list-criteria"/>
					
                    <fo:block font-weight="bold" font-size="8pt" space-after="0.2cm">Resultado de la Búsqueda</fo:block>
                    <xsl:apply-templates select="list-result"/>
					
                    <fo:block id="last-page"/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
	
    <xsl:template match="list-criteria">
        <fo:table table-layout="fixed" border-collapse="collapse" width="17cm" font-size="8pt" space-after="0.5cm">
            <fo:table-column column-width="3cm"/>
            <fo:table-column column-width="14cm"/>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell padding="0.06cm">
                        <fo:block>NIF:</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="0.06cm">
                        <fo:block font-weight="bold">
                            <xsl:value-of select="nif"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0.06cm">
                        <fo:block>Nombre:</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="0.06cm">
                        <fo:block font-weight="bold">
                            <xsl:value-of select="nombre"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0.06cm">
                        <fo:block>1º Apellido:</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="0.06cm">
                        <fo:block font-weight="bold">
                            <xsl:value-of select="apellido1"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0.06cm">
                        <fo:block>2º Apellido:</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="0.06cm">
                        <fo:block font-weight="bold">
                            <xsl:value-of select="apellido2"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell padding="0.06cm">
                        <fo:block>Rol:</fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="0.06cm">
                        <fo:block font-weight="bold">
                            <xsl:value-of select="rol"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
		
    <xsl:template match="list-result">
        <fo:table table-layout="fixed" border-collapse="collapse" width="17cm" font-size="8pt" border="0.02cm solid #000000">
            <fo:table-column column-width="1.8cm"/>
            <fo:table-column column-width="3.5cm"/>
            <fo:table-column column-width="3.5cm"/>
            <fo:table-column column-width="3.5cm"/>
            <fo:table-column column-width="3cm"/>
            <fo:table-column column-width="1.7cm"/>
            <fo:table-header>
                <fo:table-row display-align="center" text-align="center" font-weight="bold">
                    <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                        <fo:block>NIF</fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                        <fo:block>NOMBRE</fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                        <fo:block>1º APELLIDO</fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                        <fo:block>2º APELLIDO</fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                        <fo:block>ROL</fo:block>
                    </fo:table-cell>
                    <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                        <fo:block>F. ALTA</fo:block>
                    </fo:table-cell>
                </fo:table-row>											
            </fo:table-header>
            <fo:table-body>
                <xsl:for-each select="result-item">
                    <fo:table-row display-align="center">
                        <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                            <fo:block>
                                <xsl:value-of select="nif"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                            <fo:block>
                                <xsl:value-of select="nombre"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                            <fo:block>
                                <xsl:value-of select="apellido1"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                            <fo:block>
                                <xsl:value-of select="apellido2"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                            <fo:block>
                                <xsl:value-of select="rol"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="0.02cm solid #000000" padding="0.06cm">
                            <fo:block>
                                <xsl:value-of select="fecha-alta"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    
    <xsl:template name="tokenize">
        <xsl:param name="cadena" />
        <xsl:param name="separador" />
        <!-- concatena el separador para el último token -->
        <xsl:variable name="token" select="normalize-space(substring-before(concat($cadena, $separador), $separador))"/> 
        <xsl:if test="$token">
            <fo:block>
                <xsl:value-of select="$token"/>
            </fo:block>
            <xsl:call-template name="tokenize"> 
                <xsl:with-param name="cadena" select="normalize-space(substring-after($cadena, $separador))"/> 
                <xsl:with-param name="separador" select="$separador"/> 
            </xsl:call-template>    
        </xsl:if>  
    </xsl:template>
</xsl:stylesheet>