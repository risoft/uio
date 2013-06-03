Ext.define('uio.desktop.UioGrid', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.grid.column.Action', 'uio.desktop.UioStore', 'uio.desktop.UioGridButton'
    ],
    xtype: 'uiogrid',
    stateful: true,
    multiSelect: true,
    stateId: 'stateGrid',
    height: 300,
    viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },
    
    toggleGridButtons: function()
    {
    	var selection = this.getSelectionModel().getSelection();
    	var toolbar = this.down('toolbar');
    	if (!toolbar) return;
		var buttons = toolbar.items.items;

		Ext.each(buttons, function(obj, index){
			console.log(obj);
			console.log(obj.xtype);
			if (obj.xtype == "uiogridbutton")
			{
				obj.setDisabledBySelectionSize(selection.length);
			}
		});
    },
    
    initComponent: function()
    {
    	this.callParent();
    	this.getSelectionModel().addListener('selectionchange',
    			function(model, selection, opts)
    			{
    				this.toggleGridButtons();
    			}, this
    			);
    	this.toggleGridButtons();
    },
    
    constructor: function(config)
    {
        config.store = Ext.create('uio.desktop.UioStore', {jclass: config.jclass, url: config.url || undefined});
        config.columns =uio.formdefs[config.jclass].gridColumns;
        config.title=uio.formdefs[config.jclass].gridTitle;
    	this.initConfig(config);
    	this.callParent(arguments);
    }
 
});
